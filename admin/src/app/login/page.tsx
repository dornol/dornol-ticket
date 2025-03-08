import { signIn } from "@/auth";

export default function LoginPage() {

  return (
    <>
      <div className="flex items-center justify-center min-h-screen bg-gray-100">
        <form
          action={async (formData) => {
            "use server";
            const result = await signIn('credentials', {
              username: formData.get('username'),
              password: formData.get('password'),
              redirect: true,
              redirectTo: '/'
            })
          }}
          className="w-full max-w-sm p-6 bg-white rounded-lg shadow-lg"
        >
          <h2 className="text-2xl font-bold text-center text-gray-700 mb-6">Login</h2>

          <div className="mb-4">
            <label htmlFor="username" className="block text-sm font-medium text-gray-600 mb-2">
              Username
            </label>
            <input
              type="text"
              name="username"
              required
              className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div className="mb-6">
            <label htmlFor="password" className="block text-sm font-medium text-gray-600 mb-2">
              Password
            </label>
            <input
              type="password"
              name="password"
              required
              className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <button
            type="submit"
            className="w-full py-3 bg-blue-500 text-white font-semibold rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            Log In
          </button>
        </form>
      </div>
    </>
  )
}